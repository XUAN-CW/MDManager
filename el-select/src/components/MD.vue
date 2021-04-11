<template>
  <div>
    <el-input v-model="search.title" placeholder="按标题搜索"></el-input>
    <el-button type="primary" @click="search.more = !search.more"
      >标签/目录搜索</el-button
    >
    <div v-show="this.search.more">
      <el-checkbox-group v-model="search.checkedTags">
        <el-checkbox v-for="tag in allTag" :label="tag" :key="tag">{{
          tag
        }}</el-checkbox>
      </el-checkbox-group>
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
                if (article.tags.findIndex((item) => item === search.checkedTags[i]) != -1) {
                  tagFilter = true;
                }else{
                  tagFilter = false;
                }
              }
            } else {
              tagFilter = false;
            }
          }

          return titleFilter === tagFilter;
        })
      "
      style="width: 100%"
    >
      <el-table-column label="姓名">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>标题: {{ scope.row.title }}</p>
            <p>标签: {{ scope.row.tags }}</p>
            <p>目录: {{ scope.row.categories }}</p>
            <div slot="reference">
              <el-tag size="medium">{{ scope.row.title }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="right">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="danger"
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
        // console.log(res.data);
        console.log(this.search.checkedTags == "");
        console.log(this.articles.length);
      })
      .catch((err) => {
        console.log(err);
      });
  },

  methods: {
    handleEdit(index, row) {
      console.log(this.search.more);
      console.log(index, row);
    },
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