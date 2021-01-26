<template>
<div>
    <span> -- </span>
</div>
</template>

<script>
import axios from 'axios'
const rest_url = "http://localhost:8085/services/multichat/2"
export default {
    data() {
        return {
            canalDesc: {
                canalId: '',
                canalDesc: ''
            }
        }
    },
    mounted() {
        axios.get(rest_url)
            .then((response) => {console.log(response);
            this.canalDesc = response.data;
        }
        );
    }
}
</script>