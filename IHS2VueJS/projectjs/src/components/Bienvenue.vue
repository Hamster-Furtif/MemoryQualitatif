<template>
<div id = 'App'>
    <h2> Bienvenue {{ montitre }} </h2>
    <h5>
        <span v-if="!this.$store.getters.getMonUser==''">
            <Navigate :url="urlmaison" desc='Maison'/>
        </span>
        <span v-if="!this.$store.getters.getMonUser==''"> - </span>
        <span v-if="!this.$store.getters.getMonUser==''"> discussion </span>
    </h5>
<div v-if="!$route.params.leuser == ''" id='AppCore'>
    <router-view/>
</div>
<div v-else>
    <Lelogin @loginEvent='modifTitre'/>
</div>
</div>
</template>


<script>
import Lelogin from '@/components/Login.vue'
export default {
    components:{
        Lelogin
    },
    //data est interne au composant, si on veut exposer des trucs, faut des props 
    data() {
        return {
            montitre : 'test'
        }
    },
    props: {
        montitrep : String
    },
    // !!!!! ON A PAS LE DROIT DE MODIFIER LE COMPOSANT PARENT DU PROP !!!!!
    // Ne fonctionne pas : car le if est réalisé à la création
    created() {
        if (!this.$store.getters.getMonUser == ''){
            // interdit : this.montitrep = this.$route.params.leuser;
            this.montitre = this.$store.getters.getMonUser;
        } else {
            this.montitre = this.montitrep;
        }
    },
    methods:{
        modifTitre(){
            this.updateAll();
        },
        updateAll() {
            this.montitre = this.$store.getters.getMonUser;
            this.urlmaison = this.$store
        }
    }
}
</script>