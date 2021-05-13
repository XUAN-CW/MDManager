<template>
  <div>
    <el-table :data="articleIncurrentPage()">
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
      <el-table-column label="操作" width="200%">
        <template slot-scope="scope">
          <el-button size="mini" type="primary">
            <el-popover trigger="click" placement="top" width="1000">
              <span>标签：</span>
              <div slot="reference">编辑</div>
            </el-popover>
          </el-button>
          <el-button size="mini" type="primary" @click="openFile(scope.$index, scope.row)">
            <el-popover trigger="hover" placement="top">
              <h4>{{ scope.row.title }}</h4>
              <p>路径：{{ scope.row.path }}</p>
              <div slot="reference">打开</div>
            </el-popover>
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="showArticles.currentPage" :page-sizes="[5, 10, 20, 40]" :page-size="showArticles.pagesize" layout="total,sizes,prev,pager,next,jumper" :total="markdownArticles.length">
    </el-pagination>
  </div>
</template>

<script>
export default {

  props: {
    markdownArticles: {}
  },
  data() {
    return {
      showArticles: {
        currentPage: 1, //默认页码为1
        pagesize: 10, //默认一页显示10条
      },
    }
  },


  watch: {
    articles: {
      handler(articles) {
        console.log(articles)
      },
    },
  },
  mounted() {
    console.log(this.markdownArticles)
  },

  methods: {

    articleIncurrentPage() {
      return this.markdownArticles.slice((this.showArticles.currentPage - 1) * this.showArticles.pagesize, this.showArticles.currentPage * this.showArticles.pagesize)

    },

    handleSizeChange(size) {
      //一页显示多少条
      this.showArticles.pagesize = size;
    },
    handleCurrentChange(currentPage) {
      //页码更改方法
      this.showArticles.currentPage = currentPage;
    },
  },
}
</script>