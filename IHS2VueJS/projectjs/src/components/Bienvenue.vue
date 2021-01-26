<template>
<div id = 'App'>
    <h2> Bienvenue {{ montitrep }} </h2>
<div id='AppCore'>
    <router.view/>
</div>
</div>
</template>


<script>
export default {

}
</script>

<style>

</style>

<script>
export default {
    //data est interne au composant, si on veut exposer des trucs, faut des props 
    data() {
        return {
            montitre : 'test'
        }
    },
    props: {
        montitrep : String
    }
}
</script>