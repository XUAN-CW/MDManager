<template>
  <div>
    <el-form ref="form" :model="form" >
      <el-form-item label="知识点">
        <el-select 
          v-model="form.knowledge"
          clearable
          multiple
          filterable
          allow-create
          default-first-option
          placeholder="输入知识点内容，用回车添加"
          size="100"
        >
          <el-option
            v-for="item in knowledge"
            :key="item.value"
            :label="item.label"
            :value="item.label"
          ></el-option>
        </el-select>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      knowledge: [
        { label: "知识点1" },
        { label: "知识点2" },
        { label: "知识点3" },
      ],
      form: {},
    };
  },
};
</script>

<style scoped>
.el-select {
  width: 80%;
}
</style>