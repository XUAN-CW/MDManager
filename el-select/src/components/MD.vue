<template>
  <el-table :data="articles" style="width: 100%">
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
          @click="handleDelete(scope.$index, scope.row)"
          >删除</el-button
        >
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      articles: [],
      tableData: [
        {
          date: "2016-05-02",
          name:
            "王小虎1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄",
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄",
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄",
        },
      ],
    };
  },

  created() {
    axios
      .get("http://localhost:8545/getArticle")
      .then((res) => {
        console.log(res)
        this.articles = res.data
        console.log(res.data)
        console.log(this.articles)
      })
      .catch((err) => {
        console.log(err);
      });
  },

  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
  },
};
</script>