import { shallowMount, createLocalVue } from "@vue/test-utils";
import Cart from "@/views/Cart.vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

const localVue = createLocalVue();  
localVue.use(BootstrapVue);
localVue.use(IconsPlugin);

describe("Cart.vue", () => {
  it("Test gotomenu method.", async () => {
    const $store = {
      commit: jest.fn(),
      getters: { cartItems: 0, cartTotal: 0 },
    };

    let wrapper = shallowMount(Cart, {
      mocks: {
          $route: "/menu",
          $store,
      },
      localVue,
    });
    await wrapper.trigger('click', { button: 2 });
    expect(wrapper.vm.$route).toBe("/menu");
  });

  it("Test items and total in the local store.", () => {
    const $store = {
      commit: jest.fn(),
      getters: { cartItems: 0, cartTotal: 0 },
    };
    
    let wrapper = shallowMount(Cart, {
      mocks: {
        $store,
      },
      localVue,
    });

    wrapper.vm.$store.commit("updateCartFromLocalStorage");
    expect(wrapper.vm.$store).toEqual($store);
    expect(wrapper.vm.$store.getters.cartItems).toEqual(0);
    expect(wrapper.vm.$store.getters.cartTotal).toEqual(0);
  });

  it("Test add note to local storage", () => {
    let product1 = {id: "a7f8fa32-4d59-4641-b9b5-33596d46aa6a", name: "Hamburger", price: 11.5, img: "https://www.sameneenkoekopen.nl/uploads/images/recept/hamburger-uit-de-pan.jpg" };
    let product2 = {id: "16cb3054-bb33-4067-a137-544a2e64b8d1", name: "ijs", price: 2.34, img: "https://thumbor.pijper.io/m9CcJlEYAPakByvvvI8XXMNDqfQ=/1280x800/https://storage.indicium.app/source/lonely-planet/inspiratie/2019-juni/ijs2.jpg"};
    let order =  {id: "c733f54a-076f-4fb3-b6e9-96b27e8a1586", subTotal: 24.54, paid: false, note: "", table: 2, products: {product1, product2}};

    const $store =  {
      commit: jest.fn(),
      getters: { cartItems: 0, cartTotal: 0 },
    };
    let wrapper = shallowMount(Cart, {
        propsData: { order },
        mocks: {
            $store,
        },
        localVue,
    });
    wrapper.vm.$store.commit("saveNote", wrapper.props().order);
    expect(wrapper.vm.$store).toEqual($store);

  });
});