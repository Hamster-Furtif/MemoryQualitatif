<template>
    <form @submit="VerifForm">
        <p>
            <label for="nom">Nom: </label>
            <input id="nom" v-model="nom" name="nom">
        </p>
        <p>
            <input type="submit" value="Go !!">
        </p>
    </form>
</template>

<script>
export default {
    data() {
        return {
            nom: ""
        };
    },
    methods: {
        VerifForm() {
            if (!this.nom == '') {
                this.$store.commit('setMonUser', this.nom);
                this.$emit('loginEvent'); //this.$emit permet de déclencher un évenement
                this.$router.push({name: 'Maison', params:{leuser : this.nom}})
            }
        }
    }
}
</script>