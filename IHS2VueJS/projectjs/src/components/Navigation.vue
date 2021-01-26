<template>
    <router-link :to="url">{{desc}} </router-link>
</template>
<script>
export default {
    props: {
        url:String,
        desc:String
    }
}
</script>