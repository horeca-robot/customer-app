import { shallowMount, createLocalVue } from "@vue/test-utils";
import CartItemCard from "@/components/CartItemCard.vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

const localVue = createLocalVue();  
localVue.use(BootstrapVue);
localVue.use(IconsPlugin);

describe("CartItemCard.vue", () => {
  it("Test inserting a product.", () => {
    let product = { name: "Hamburger", price: 15.95, img: "url", ingredients: { id: 1, name: "kaas" } };
    let wrapper = shallowMount(CartItemCard, {
        propsData: { product },
        localVue,
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
        },
        localVue,
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
        },
        localVue,
    });
    
    wrapper.vm.$store.commit("addToCart", product);
    expect(wrapper.vm.$store).toEqual($store);
    wrapper.vm.$store.commit("removeFromCart", product);
    expect(wrapper.vm.$store).toEqual($store);
  });

  it("Test changing icon.", () => {
    let product = { name: "Hamburger", price: 15.95, img: "url", ingredients: { id: 1, name: "kaas" } };

    let wrapper = shallowMount(CartItemCard, {
        propsData: { product },
        data() {
          return {
            sortDirection: "asc",
            hidden: false,
          }
        },
        localVue,
    });

    wrapper.vm.changeIcon();
    expect(wrapper.vm.sortDirection).toEqual("desc");
    expect(wrapper.vm.hidden).toEqual(true);
  });
});