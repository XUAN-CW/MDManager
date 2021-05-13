<template>
  <el-select v-model="value" multiple filterable allow-create default-first-option placeholder="请选择">
    <el-option v-for="item in options" :key="item.index" :label="item" :value="item">
    </el-option>
  </el-select>
</template>

<script>
export default {

  props: {
    options: {}
  },
  data() {
    return {
      value: []
    }
  },
}
</script>