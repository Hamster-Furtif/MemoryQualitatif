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
        verifForm() {}
    }
}
</script>