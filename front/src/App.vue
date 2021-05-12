<template>
  <div id="app">
    <SelectInput ref="selectInput" :options="search.options"></SelectInput>
    <MarkdownTable v-if="articles!=null" :markdownArticles="filteringByInputValue()"></MarkdownTable>
  </div>
</template>

<script>
import SelectInput from '@/components/SelectInput'
import MarkdownTable from '@/components/MarkdownTable'

import axios from "axios";

export default {
  name: 'App',
  components: {
    SelectInput,
    MarkdownTable
  },
  data() {
    return {
      articles: [],
      search: {
        options: [],
        value: [],
      },
    }
  },

  watch: {
    articles: {
      handler(articles) {
        let searchOptions = [];
        for (let i = 0; i < articles.length; i++) {
          if (articles[i].tags != null) {
            searchOptions = searchOptions.concat(articles[i].tags);
          }
          if (articles[i].categories != null) {
            searchOptions = searchOptions.concat(articles[i].categories);
          }
        }
        this.search.options = Array.from(new Set(searchOptions));
      },
    },
  },

  created() {
    axios.get("http://localhost:8545/getArticles", {
    }).then((res) => {
      this.articles = res.data.data.articles
      // console.log(this.articles)
    }).catch((err) => {
      console.log(err);
    });
  },
  methods: {
    filteringByInputValue() {
      // console.log("search.value");
      return this.articles.filter((article) => {
        if (this.search.value == "") {
          return true;
        }
        return this.search.value.every((currentValue) => {
          let currentValueInTags = article.tags == null ? false : article.tags.findIndex((item) => item === currentValue) != -1;
          let currentValueInCategories = article.categories == null ? false : article.categories.findIndex((item) => item === currentValue) != -1;
          let currentValueInTitle = JSON.stringify(article.title).includes(currentValue);
          return currentValueInTags || currentValueInCategories || currentValueInTitle;
        });
      });

    },
  },
  mounted() {
    this.$watch('$refs.selectInput.value', (inputValue) => {
      this.search.value = inputValue
      console.log(this.search.value)
      })
  }
}
</script>

<style>
</style>
