import { shallowMount } from "@vue/test-utils";
import Cart from "@/views/Cart.vue";

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
      }
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
    });

    wrapper.vm.$store.commit("updateCartFromLocalStorage");
    expect(wrapper.vm.$store).toEqual($store);
    expect(wrapper.vm.$store.getters.cartItems).toEqual(0);
    expect(wrapper.vm.$store.getters.cartTotal).toEqual(0);
  });
});