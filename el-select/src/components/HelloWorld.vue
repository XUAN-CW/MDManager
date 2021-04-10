<template>
  <div>
    <el-form ref="form">
      <el-select
        v-model="value"
        multiple
        filterable
        allow-create
        default-first-option
        placeholder="输入知识点内容，用回车添加"
        size="100"
        :remote-method="remoteMethod"
        :loading="loading"
        @visible-change="logValue($event)"
      >
        <el-option
          v-for="item in list"
          :key="item.value"
          :label="item.label"
          :value="item.label"
        ></el-option>
      </el-select>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      options: [],
      value: [],
      list: [],
      loading: false,
      states: [
        "Alabama",
        "Alaska",
        "Arizona",
        "Arkansas",
        "California",
        "Colorado",
        "Connecticut",
        "Delaware",
        "Florida",
        "Georgia",
        "Hawaii",
        "Idaho",
        "Illinois",
        "Indiana",
        "Iowa",
        "Kansas",
        "Kentucky",
        "Louisiana",
        "Maine",
        "Maryland",
        "Massachusetts",
        "Michigan",
        "Minnesota",
        "Mississippi",
        "Missouri",
        "Montana",
        "Nebraska",
        "Nevada",
        "New Hampshire",
        "New Jersey",
        "New Mexico",
        "New York",
        "North Carolina",
        "North Dakota",
        "Ohio",
        "Oklahoma",
        "Oregon",
        "Pennsylvania",
        "Rhode Island",
        "South Carolina",
        "South Dakota",
        "Tennessee",
        "Texas",
        "Utah",
        "Vermont",
        "Virginia",
        "Washington",
        "West Virginia",
        "Wisconsin",
        "Wyoming",
        "北京",
        "天津",
        "上海",
        "重庆",
        "河北",
        "山西",
        "辽宁",
        "吉林",
        "黑龙江",
        "江苏",
        "浙江",
        "安徽",
        "福建",
        "江西",
        "山东",
        "河南",
        "湖北",
        "湖南",
        "广东",
        "海南",
        "四川",
        "贵州",
        "云南",
        "陕西",
        "甘肃",
        "青海",
        "内蒙古",
        "广西",
        "西藏",
        "宁夏",
        "新疆维吾尔自治区",
        "香港",
        "澳门",
        "台湾",
      ],
    };
  },
  mounted() {
    this.list = this.states.map((item) => {
      return { value: `value:${item}`, label: `${item}` };
    });
  },
  methods: {
    remoteMethod(query) {
      if (query !== "") {
        this.loading = true;
        setTimeout(() => {
          this.loading = false;
          this.options = this.list.filter((item) => {
            return item.label.toLowerCase().indexOf(query.toLowerCase()) > -1;
          });
        }, 200);
      } else {
        this.options = [];
      }
    },

    checkBlur(e) {
      console.log(e.target.value);
    },

    logValue(e) {
      //下拉框隐藏时触发
      if(!e){
      console.log(this.value);
      }
    },
  },
};
</script>

<style scoped>
.el-select {
  width: 80%;
}
</style>