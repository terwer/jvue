<template>
    <div id="app">
        <Index/>
    </div>
</template>

<script>
    // import 'bootstrap/dist/css/bootstrap.css'
    // import 'bootstrap-vue/dist/bootstrap-vue.css'
    import Index from './components/themes/default/Index.vue'

    export default {
        name: "App",
        components: {
            Index
        }
    }
</script>

<style scoped>
    #app {
        font-family: 'Microsoft YaHei', sans-serif;
        height: 1080px;
    }
</style>