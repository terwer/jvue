<template>
    <div id="app">
        <input v-model="message"
               type="text"/>
        <button @click="showMessage">显示信息</button>
        <h2 class="message">{{ message }}</h2>
    </div>
</template>

<script>
    export default {
        name: "App",
        data() {
            return {
                message: 'Hello World',
            };
        },
        methods: {
            showMessage() {
                alert(this.message)
            }
        }
    }
</script>

<style scoped>
    #app {
        font-size: 18px;
        font-family: 'Roboto', sans-serif;
        color: blue;
    }

    .message {
        font-size: 18px;
        font-family: 'Roboto', sans-serif;
        color: red;
    }
</style>