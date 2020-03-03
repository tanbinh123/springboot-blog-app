<template>
  <div>
    <div class="table">
      <b-container>
        <b-table
          @row-selected="onRowSelected"
          selectable
          select-mode="single"
          :busy.sync="isBusy"
          hover
          head-variant="light"
          :items="this.$store.state.guestbook"
          :fields="fields"
        >
          <template v-slot:table-busy>
            <div class="text-center text-danger my-2">
              <b-spinner class="align-middle"></b-spinner>
              <strong>Loading...</strong>
            </div>
          </template>
        </b-table>
      </b-container>
      <b-container>
        <b-button variant="success" class="mr-3" size="sm" @click="showDetails">View post</b-button>
        <b-button :variant="deleteBtnColor" class="my-3" size="sm" @click="deleteRow">Delete post</b-button>
      </b-container>
    </div>
  </div>
</template>
<script>
import axios from "@/plugins/axios"
export default {
  name: 'Guestbook',
  data () {
    return {
      selected: [],
      fields: [
        {
          key: "username",
          sortable: true
        },
        {
          key: "country",
          sortable: true
        },
        {
          key: "date",
          sortable: true
        },
        {
          key: "title",
          sortable: true
        }
      ]
    }
  },
  methods: {
    onRowSelected (items) {
      this.selected = items
      this.$store.state.details = items
    },
    deleteRow () {
      if (this.selected.length > 0) {
        var row = this.selected[0].id
        axios.deleteRow(row).then(res => {
          this.$store.state.guestbook = res.data
        })
      }
    },
    showDetails () {
      if (this.selected.length > 0) {
        this.$router.push('/details')
      }
    }
  },
  computed: {
    deleteBtnColor () {
      if (this.selected.length > 0)
        return 'danger'
      else return null
    },
    guestbook () {
      return this.$store.state.guestbook
    },
    isBusy () {
      return this.$store.state.busy
    }
  }
}
</script>

<style scoped>
.table {
  margin: 0 auto;
  max-width: 60rem;
}
</style>