<template>
  <div class="home">
    <b-container
      class="bv-example-row"
      v-for="(item, index) in this.$store.state.details"
      :key="index"
    >
      <b-card no-body>
        <b-card-header>
          <b-row>
            <b-col>
              <h4>{{item.username}}</h4>
            </b-col>
            <b-col>
              <h6>{{item.country}}</h6>
              <h6>{{item.date}}</h6>
            </b-col>
          </b-row>
        </b-card-header>
        <b-row>
          <b-col md>
            <b-img class="image" fluid :src="item.url" img-alt="Card image"></b-img>
          </b-col>
          <b-col>
            <h3 class="mt-3 mx-3" style="text-align:left">{{item.title}}</h3>
            <p class="mt-3 mx-3" style="text-align:left">{{item.message}}</p>
          </b-col>
        </b-row>
        <b-card-body class="text-center">
          <b-button variant="success" router-link to="/guestbook">Back</b-button>
        </b-card-body>
      </b-card>
    </b-container>
  </div>
</template>

<script>

export default {
  name: 'Details',
  data () {
    return {
      isLoaded: false,
    }
  },
  methods: {
    onImgLoad () {
      this.isLoaded = true
    }
  },
  mounted () {
    if (this.$store.state.details.length == 0) {
      this.$router.push('/')
    }
  }
}
</script>
<style scoped>
.home {
  text-align: center;
}
.image {
  max-width: 300px;
}
</style>
