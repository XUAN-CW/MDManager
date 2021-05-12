<template>
  <div id="app">
    <SelectInput ref="selectInput" :options="options"></SelectInput>
  </div>
</template>

<script>
import SelectInput from '@/components/SelectInput'

import axios from "axios";

export default {
  name: 'App',
  components: {
    SelectInput,
  },
  data() {
    return {
      options: ['11', '22', '33'],
      value: '',
      articles: []
    }
  },

  watch: {
    articles: {
      handler(articles) {
        let searchOptions = [];
        // let tagConcatResult = [];
        // let categoryConcatResult = [];
        for (let i = 0; i < articles.length; i++) {
          searchOptions = searchOptions.concat(articles[i].tags == null ? "null" : articles[i].tags);
          searchOptions = searchOptions.concat(articles[i].categories == null ? "null" : articles[i].categories);

          // tagConcatResult = tagConcatResult.concat(articles[i].tags == null ? "null" : articles[i].tags);
          // categoryConcatResult = categoryConcatResult.concat(articles[i].categories == null ? "null" : articles[i].categories);
        }
        // this.allTag = Array.from(new Set(tagConcatResult)); //要返回的数据
        // this.allCategory = Array.from(new Set(categoryConcatResult));
        this.options = Array.from(new Set(searchOptions));
      },
      immediate: true,
      deep: true,
    },
  },

  created() {
    axios.get("http://localhost:8545/getArticles", {
    }).then((res) => {
      this.articles = res.data.data.articles
      console.log(this.articles)
    }).catch((err) => {
      console.log(err);
    });
  },

  mounted() {
    this.$watch('$refs.selectInput.value', (newVal) => {
      console.log(newVal)
    })
  }
}
</script>

<style>
</style>
