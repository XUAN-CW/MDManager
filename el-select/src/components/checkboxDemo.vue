<template>
  <el-checkbox-group v-model="checkList">
    <el-checkbox label="复选框 A"
    @change="logValue"></el-checkbox>
    <el-checkbox label="复选框 B"></el-checkbox>
    <el-checkbox label="复选框 C"></el-checkbox>
    <el-checkbox label="禁用" disabled></el-checkbox>
    <el-checkbox label="选中且禁用" disabled></el-checkbox>
  </el-checkbox-group>
</template>

<script>
export default {
  data() {
    return {
      checkList: ["选中且禁用", "复选框 A"],
    };
  },
  methods: {

    logValue(e) {
      //下拉框隐藏时触发
      console.log(e);
    //   if(!e){
    //   console.log(this.value);
    //   }
    },
  },
};
</script>