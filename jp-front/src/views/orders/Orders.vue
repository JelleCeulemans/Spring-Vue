<template>
  <div class="container mt-5">
    <h1>Orders</h1>

    <table class="table table-hover">
      <thead>
      <tr>
        <th scope="col">order #</th>
        <th scope="col">Product</th>
        <th scope="col">Price</th>
        <th scope="col">Amount</th>
        <th scope="col">Total</th>
        <th scope="col">Edit</th>
        <th scope="col">Delete</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(order, index) in orders">
        <th scope="row">{{index + 1}}</th>
        <td>{{order.productName}}</td>
        <td>€ {{order.price}}</td>
        <td>{{order.amount}}</td>
        <td>€ {{order.price * order.amount}}</td>
        <td>
          <button @click="editOrder(order.id)" class="btn btn-success">
            <font-awesome-icon icon="edit"/>
          </button>
        </td>
        <td>
          <button @click="deleteOrder(order.orderID)" class="btn btn-danger">
            <font-awesome-icon icon="trash"/>
          </button>
        </td>
      </tr>
      </tbody>
    </table>
    <p id="total">Total: € {{total}}</p>
    <!-- Modal -->
    <div class="modal fade" tabindex="-1" role="dialog" id="editModal">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Edit order</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <label for="amount">Amount</label>
              <input type="number" class="form-control" id="amount" v-model="order.amount">
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" @click="changeOrder">Save Changes</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import OrdersAPI from '../../services/API/OrdersAPI';
  import { mapState } from 'vuex';

  export default {
    data() {
      return {
        orders: [],
        total: 0,
        order: {},
      };
    },
    methods: {
      loadOrders() {
        //FIXME getOrderByUserId
        OrdersAPI.getAllOrders()
          .then(res => {
            const data = res.data;
            const orders = [];
            let total = 0;
            for (let key in data) {
              const order = data[key];
              total += order.amount * order.price;
              order.id = key;
              orders.push(order);
            }
            this.orders = orders;
            this.total = total;
          })
          .catch(error => {
            console.log(error);
          });
      },
      editOrder(id) {
        const order = {
          ...this.orders[id]
        };
        delete order.id;
        this.order = order;
        console.log(this.order);
        $('#editModal').modal('show');
      },
      deleteOrder(id) {
        const order = {
          ...this.orders[id]
        };
        OrdersAPI.deleteOrder(id)
          .then(res => this.loadOrders())
          .catch(error => console.log(error));
      },
      changeOrder() {
        OrdersAPI.changeOrder(this.order)
          .then(res =>{
            $('#editModal').modal('hide');
            this.loadOrders();
          })
          .catch(error => console.log(error));
      }
    },
    mounted() {
      this.loadOrders();
    },
    computed: {
      ...mapState({
        user: state => state.user.loggedInUser,
      }),
    },
  };
</script>

<style scoped>
  #total {
    font-weight: bold;
    text-align: right;
    font-size: 30px;
  }
</style>
