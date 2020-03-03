import axios from 'axios'

const AXIOS = axios.create({
    baseURL: '/api',
    timeout: 5000,
    headers: { 'Content-Type': 'application/json' }
});

export default {
    getGuestbook () {
        return AXIOS.get(`/guestbook/`);
    },
    postForm (form) {
        return AXIOS.post(`/newmessage/`, form);
    },
    deleteRow (id) {
        return AXIOS.get(`/delete/` + id);
    }
};
