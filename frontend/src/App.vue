<template>
  <div id="app">
    <div id="nav">
      <b-nav tabs>
        <b-nav-item router-link to="/">Home</b-nav-item>
        <b-nav-item router-link to="/guestbook">View posts</b-nav-item>
        <b-nav-item router-link to="/add">Add entry</b-nav-item>
      </b-nav>
    </div>
    <router-view />
  </div>
</template>
<script>
import axios from "@/plugins/axios"
export default {
  name: 'App',
  mounted () {
    axios.getGuestbook()
      .then(res => {
        this.$store.state.guestbook = res.data
        this.$store.state.busy = false
      })
  }
}
</script>

<style>
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

#nav {
  padding: 30px;
}

#nav a {
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
