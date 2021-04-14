<template>
  <div id="MD">
    <div id="serachDiv">
      <div id="search_basics">
        <el-select
          v-model="search.value"
          multiple
          filterable
          allow-create
          placeholder="请选择"
          @visible-change="setSearchOptions($event)"
        >
          <el-option-group
            v-for="group in search.options"
            :key="group.label"
            :label="group.label"
          >
            <el-option
              v-for="item in group.options"
              :key="item.index"
              :label="item"
              :value="item"
            >
            </el-option>
          </el-option-group>
        </el-select>
      </div>
    </div>
    <el-table
      :data="
        articles.filter((article) => {
          // let titleFilter = JSON.stringify(article)
          //   .toLowerCase()
          //   .includes(search.title.toLowerCase());

          // let tagFilter = true;
          // if (this.search.checkedTags != '') {
          //   if (article.tags != null) {
          //     for (let i = 0; i < search.checkedTags.length && tagFilter; i++) {
          //       if (
          //         article.tags.findIndex(
          //           (item) => item === search.checkedTags[i]
          //         ) != -1
          //       ) {
          //         tagFilter = true;
          //       } else {
          //         tagFilter = false;
          //       }
          //     }
          //   } else {
          //     tagFilter = false;
          //   }
          // }
          if (search.value == '') {
            return true;
          }
          return search.value.every((currentValue) => {
          //如果 currentValue 存在于 (allTag+allCategory) 之中，则判定其为 tag 或 category
          //如果 currentValue 不存在于 (allTag+allCategory) 之中，则判定其为标题
            let isTag = allTag.findIndex((item) => item === currentValue) != -1;
            let isCategory = allCategory.findIndex((item) => item === currentValue) != -1;
            if (isTag || isCategory) {
              if (isTag) {
                return (article.tags.findIndex((item) => item === currentValue) != -1);
              }
              if (isCategory) {
                return (article.categories.findIndex((item) => item === currentValue) != -1);
              }
            } else {
              return JSON.stringify(article.title).includes(currentValue);
            }
          });
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
      <el-table-column label="操作" width="200%" id="table_operate">
        <template slot-scope="scope">
          <el-button size="mini" type="primary">
            <el-popover trigger="click" placement="top" width="1000">
              <span>标签：</span>
              <el-select
                v-model="scope.row.tags"
                multiple
                filterable
                allow-create
                placeholder="请选择"
                @visible-change="changeValue1($event)"
                style="width: 95%"
              >
                <el-option
                  v-for="item in edit.editTag"
                  :key="item.index"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
              <div slot="reference">编辑</div>
            </el-popover></el-button
          >
          <el-button
            size="mini"
            type="primary"
            @click="openFile(scope.$index, scope.row)"
          >
            <el-popover trigger="hover" placement="top">
              <h4>{{ scope.row.title }}</h4>
              <p>路径：{{ scope.row.path }}</p>
              <div slot="reference">打开</div>
            </el-popover></el-button
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
      scanPath: [],
      articles: [],
      search: {
        options: [
          {
            label: "tags",
            options: [],
          },
          {
            label: "categories",
            options: [],
          },
        ],
        value: [],
      },
      edit: {
        editTag: [],
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
    allCategory() {
      return ["aa", "bb"]; //要返回的数据
    },
  },

  created() {
    axios
      .get("http://localhost:8545/getScanPath")
      .then((res) => {
        console.log(res);
        this.scanPath = res.data.data.scanPath;
        // console.log(this.scanPath);

        this.scanPath.forEach((element) => {
          // console.log(element);
          axios
            .get("http://localhost:8545/getArticle", {
              params: {
                dir: element,
              },
            })
            .then((res) => {
              this.articles = this.articles.concat(res.data.data.articles);
              // console.log(JSON.stringify(this.articles));
            })
            .catch((err) => {
              console.log(err);
            });
        });
      })
      .catch((err) => {
        console.log(err);
      });
  },

  methods: {
    openFile(index, row) {
      console.log(index, row.path);
      axios
        .get("http://localhost:8545/openFile", {
          params: {
            path: row.path,
          },
        })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    changeValue1(callback) {
      //只有回调参数为false时才触发 ctx.getAreaListDataSearch(vc,1)这个函数;
      if (callback) {
        this.edit.editTag = this.allTag;
      }
    },
    setSearchOptions(callback) {
      if (callback) {
        this.search.options[0].options = this.allTag;
        console.log(this.allCategory);
        this.search.options[1].options = this.allCategory;
        console.log(this.search);
      } else {
        console.log(this.search.value);
      }
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
.tag_btn {
  margin-top: 10px;
  margin-left: auto;
}
</style>