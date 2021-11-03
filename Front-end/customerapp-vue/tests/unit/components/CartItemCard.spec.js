import { shallowMount } from "@vue/test-utils";
import CartItemCard from "@/components/CartItemCard.vue";

describe("CartItemCard.vue", () => {
  it("Test inserting a product.", () => {
    let product = { name: "Hamburger", price: 15.95, img: "url", ingredients: { id: 1, name: "kaas" } };
    let wrapper = shallowMount(CartItemCard, {
        propsData: { product },
    });
    expect(wrapper.text()).toMatch(product.name);
  });

  it("Test add to cart.", () => {
    let product = { name: "Hamburger", price: 15.95, img: "url", ingredients: { id: 1, name: "kaas" } };

    const $store = {
      commit: jest.fn(),
    };

    let wrapper = shallowMount(CartItemCard, {
        propsData: { product },
        mocks: {
            $store,
        }
    });
    
    wrapper.vm.$store.commit("addToCart", product);
    expect(wrapper.vm.$store).toEqual($store);
  });

  it("Test remove from cart.", () => {
    let product = { name: "Hamburger", price: 15.95, img: "url", ingredients: { id: 1, name: "kaas" } };

    const $store = {
      commit: jest.fn(),
    };

    let wrapper = shallowMount(CartItemCard, {
        propsData: { product },
        mocks: {
            $store,
        }
    });
    
    wrapper.vm.$store.commit("addToCart", product);
    expect(wrapper.vm.$store).toEqual($store);
    wrapper.vm.$store.commit("removeFromCart", product);
    expect(wrapper.vm.$store).toEqual($store);
  });
});