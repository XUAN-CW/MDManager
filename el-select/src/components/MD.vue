<template>
  <div>
    <el-input v-model="search.title" placeholder="按标题搜索"></el-input>
    <el-button type="primary" @click="search.more = !search.more"
      >标签/目录搜索</el-button
    >
    <div v-show="this.search.more">显示部分</div>
    <el-table
      :data="
        articles.filter((data) =>
          data.title.toLowerCase().includes(search.title.toLowerCase())
        )
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
      },
    };
  },

  created() {
    axios
      .get("http://localhost:8545/getArticle")
      .then((res) => {
        // console.log(res);
        this.articles = res.data;
        // console.log(res.data);
        // console.log(this.articles);
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