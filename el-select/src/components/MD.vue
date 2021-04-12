<template>
  <div id="MD">
    <div id="serachDiv">
      <div id="search_basics">
        <div id="search_input">
          <el-input v-model="search.title" placeholder="按标题搜索"></el-input>
        </div>

        <div id="search_more_btn">
          <el-button type="primary" @click="search.more = !search.more"
            >标签/目录搜索</el-button
          >
        </div>
      </div>
      <div></div>
      <div id="search_more" v-show="this.search.more">
        <el-checkbox-group v-model="search.checkedTags">
          <el-checkbox-button
            border
            size="medium"
            v-for="tag in allTag"
            class="tag_btn"
            :label="tag"
            :key="tag"
            >{{ tag }}</el-checkbox-button
          >
        </el-checkbox-group>
      </div>
    </div>
    <el-table
      :data="
        articles.filter((article) => {
          let titleFilter = article.title
            .toLowerCase()
            .includes(search.title.toLowerCase());

          let tagFilter = true;
          if (this.search.checkedTags != '') {
            if (article.tags != null) {
              for (let i = 0; i < search.checkedTags.length && tagFilter; i++) {
                if (
                  article.tags.findIndex(
                    (item) => item === search.checkedTags[i]
                  ) != -1
                ) {
                  tagFilter = true;
                } else {
                  tagFilter = false;
                }
              }
            } else {
              tagFilter = false;
            }
          }

          return titleFilter && tagFilter;
        })
      "
    >
      <el-table-column label="title">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <h4>{{ scope.row.title }}</h4>
            <p>标签: {{ scope.row.tags }}</p>
            <p>目录: {{ scope.row.categories }}</p>
            <div slot="reference">
              {{ scope.row.title }}
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="100%" id="table_operate">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="openFile(scope.$index, scope.row)"
            >打开</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      articles: [],
      search: {
        title: "",
        more: true,
        checkedTags: [],
      },
    };
  },

  computed: {
    allTag() {
      // 1. 合并所有 tag
      let tagConcatResult = [];
      for (let i = 0; i < this.articles.length; i++) {
        tagConcatResult = tagConcatResult.concat(this.articles[i].tags);
      }

      // 2. 对合并到的 tag 去重
      let result = {};
      let finalResult = [];
      for (let i = 0; i < tagConcatResult.length; i++) {
        // tagConcatResult[i].something 不能重复,达到去重效果,且这里必须知晓"something"或是其他键名
        result[tagConcatResult[i]] = tagConcatResult[i];
      }
      for (const item in result) {
        if (item != "null") {
          finalResult.push(result[item]);
        }
      }
      return finalResult; //要返回的数据
    },
  },

  created() {
    axios
      .get("http://localhost:8545/getArticle")
      .then((res) => {
        // console.log(res);
        this.articles = res.data;
        console.log(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  },

  methods: {
    openFile(index, row) {
      console.log(index, row.path);

      axios
        .post("http://localhost:8545/openFile", {
          path: row.path,
        })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style>
html {
  overflow-y: scroll;
}

#MD {
  width: 80%;
  margin: 0 auto;
}

#search_basics {
  display: inline;
}
#search_input {
  display: inline-block;
  width: 70%;
}
#search_more_btn {
  display: inline-block;
}
#search_more {
  padding-top: 20px;
}
.tag_btn{
  margin-top: 10px;
  margin-left: auto;
}
</style>