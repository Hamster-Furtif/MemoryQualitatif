import { createStore } from 'vuex'
//le store est accessible à l'ensemble des composants, il enregistre les données

export default createStore({
  state: {
    monUser : '',
  },
  mutations: {
    setMonUser(state, user) {
      state.monUser = user;
    },
  },
  getters: {
    getMonUser(state) {
      return state.monUser;
    }
  },
  actions: {
    
  },
  modules: {
  }
})
