<template>
  <div class="add">
    <b-container>
      <b-card title="Blog post application form" bg-variant="light">
        <b-form @submit="onSubmit" @reset="onReset">
          <b-form-group class="mt-3" id="input-group-1" label="Your Name:" label-for="input-1">
            <b-form-input id="input-1" v-model="form.username" required placeholder="Enter name"></b-form-input>
          </b-form-group>
          <b-form-group id="input-group-2" label="Your Country:" label-for="input-2">
            <b-form-input id="input-2" v-model="form.country" required placeholder="Enter country"></b-form-input>
          </b-form-group>
          <b-form-group class="mt-4" id="input-group-3" label="Message title:" label-for="input-3">
            <b-form-input id="input-3" v-model="form.title" required placeholder="Enter title"></b-form-input>
          </b-form-group>
          <b-form-group id="input-group-4" label="Your Message:" label-for="input-4">
            <b-form-textarea
              required
              label-for="input-4"
              id="input-4"
              v-model="form.message"
              placeholder="Enter message..."
              rows="3"
              max-rows="6"
            ></b-form-textarea>
          </b-form-group>
          <b-row>
            <b-col>
              <b-form-file
                accept=".jpg, .png, .gif"
                v-model="file"
                @change="onFileChange"
                class="my-3"
                placeholder="Upload image..."
              ></b-form-file>
              <b-button class="mr-3 mb-2" type="submit" variant="success">Submit</b-button>
              <b-button class="mb-2" type="reset" variant="danger">Reset</b-button>
            </b-col>
            <b-col>
              <b-img class="image" style="float: right;" v-if="url" :src="url" />
            </b-col>
          </b-row>
        </b-form>
      </b-card>
      <b-alert
        class="my-4 alert"
        :show="dismissCountDown"
        dismissible
        variant="success"
        @dismissed="dismissCountDown=0"
        @dismiss-count-down="countDownChanged"
      >
        <h4 class="alert-heading">Success!</h4>
        <p>Your post has been added to the list.</p>
        <hr />
        <p>Dissmissing in {{ dismissCountDown }} seconds...</p>
        <b-progress variant="success" :max="dismissSecs" :value="dismissCountDown" height="4px"></b-progress>
      </b-alert>
    </b-container>
  </div>
</template>
<script>
import moment from 'moment'
import axios from "@/plugins/axios"
export default {
  name: 'Add',
  data () {
    return {
      url: null,
      file: null,
      dismissSecs: 10,
      dismissCountDown: 0,
      showDismissibleAlert: false,
      form: {
        id: '',
        username: '',
        country: '',
        date: '',
        title: '',
        message: '',
        url: ''
      },
    }
  },
  methods: {
    onSubmit (evt) {
      evt.preventDefault()
      this.form.id = (this.$store.state.guestbook.length + 1).toString()
      this.form.date = moment(new Date()).format("YYYY/MM/DD HH:mm:ss") + " (" + Intl.DateTimeFormat().resolvedOptions().timeZone + ")"
      this.form.url = this.url
      axios.postForm(JSON.stringify(this.form)).then(res => {
        this.$store.state.guestbook = res.data
        this.showAlert();
      })
      this.resetForm()
    },
    onReset (evt) {
      evt.preventDefault()
      this.resetForm()
    },
    resetForm () {
      this.form.username = ''
      this.form.country = ''
      this.form.date = ''
      this.form.title = ''
      this.form.message = ''
      this.form.url = ''
      this.url = null
      this.file = null
    },
    countDownChanged (dismissCountDown) {
      this.dismissCountDown = dismissCountDown
    },
    showAlert () {
      this.dismissCountDown = this.dismissSecs
    },
    onFileChange (e) {
      this.file = e.target.files[0];
      this.url = URL.createObjectURL(this.file);
    }
  }
}
</script>

<style scoped>
.add {
  margin: 0 auto;
  max-width: 60rem;
}
.alert {
  max-width: 40rem;
  margin: 0 auto;
}
.image {
  max-width: 10rem;
}
</style>