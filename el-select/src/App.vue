<template>
  <div id="app">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    <CheckboxDemo/>
    <DialogForm/>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import CheckboxDemo from './components/checkboxDemo.vue'
import DialogForm from './components/DialogForm.vue'

export default {
  name: 'App',
  components: {
    HelloWorld,
    CheckboxDemo,
    DialogForm
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
