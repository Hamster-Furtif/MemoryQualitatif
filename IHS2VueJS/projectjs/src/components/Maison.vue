<template>
    <span> Coucou ... {{ this.$route.params.leuser }} </span>
</template>

<script>
export default {
    data(){
        return{
            monuser : ''
        }
    },
    mounted() {
        this.monuser = this.$store.getters.getMonUser;
    }
}
</script>