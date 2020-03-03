<template>
  <div class="home">
    <b-container>
      <div v-show="!isLoaded">
        <b-spinner variant="success" label="Spinning"></b-spinner>
      </div>
      <div v-show="isLoaded">
        <b-container>
          <b-img alt="Vue logo" fluid :src="require('../assets/spring.png')" @load="onImgLoad" />
        </b-container>
      </div>
      <HelloWorld msg="Welcome to Spring Boot blog app" />
    </b-container>
  </div>
</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'Home',
  components: {
    HelloWorld
  },
  data () {
    return {
      isLoaded: false,
    }
  },
  methods: {
    onImgLoad () {
      this.isLoaded = true
    }
  }
}
</script>
<style scoped>
.home {
  text-align: center;
}
</style>
