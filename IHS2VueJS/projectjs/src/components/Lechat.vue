<template>
<div>
    <span> {{canalDesc.canalId+ ' -- ' + canalDesc.canalName}}  </span>
    <div>
        <button @click="wsConnect"> Blablabla ... </button>
    <div>
        <span> <pre> {{allmessage}} </pre> </span>
    </div>
    <div>
        <input v-model="response"/> <button @click="sendResponse"> Send </button>
    </div>
    </div>
</div>

</template>

<script>
import axios from 'axios'
const rest_url = "http://localhost:8085/services/multichat/2"
const ws_url = "ws://localhost:8085/ws/multichat/1:Mikky"
export default {
    data() {
        return {
            ws : null,
            wsmessage : {
                canalId : '',
                lePseudo : '',
                leContenu : ''
            },
            respmessage : {
                canalId : '1',
                lePseudo : 'Mikky',
                leContenu : ''
            },
            response : '',
            canalDesc: {
                canalId: '',
                canalDesc: ''
            },
            allmessage: ''
        }
    },
    mounted() {
        axios.get(rest_url)
            .then((response) => {console.log(response);
            this.canalDesc = response.data;
        }
        );
    },
    methods: {
        sendResponse() {
            this.respmessage.leContenu = this.response;
            this.ws.send(JSON.stringify(this.respmessage));
            this.response = '';
        },
        newMessage(event) {
            console.log("Un message");
            this.wsmessage = JSON.parse(event.data);
            this.allmessage = this.allmessage +
            this.wsmessage.lePseudo + ' : ' + this.wsmessage.leContenu + '\n';
        },
        wsConnect(){
            this.ws = new WebSocket(ws_url);
            this.ws.onopen = function(event) {
                console.log("Connection réussie");
                console.log(event);
            }

            this.ws.addEventListener('message',(event)=>(this.newMessage(event)));
        }
    }
}
</script>