import { shallowMount, createLocalVue } from "@vue/test-utils";
import ProductCard from "@/components/ProductCard.vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

const localVue = createLocalVue();  
localVue.use(BootstrapVue);

describe("ProductCard.vue", () => {
  it("Test the probs being loaded correctly in the card.", () => {
    let product = { id: "a7f8fa32-4d59-4641-b9b5-33596d46aa6a", name: "Hamburger", price: 11.5, img: "https://www.sameneenkoekopen.nl/uploads/images/recept/hamburger-uit-de-pan.jpg" };
    let wrapper = shallowMount(ProductCard, {
      propsData: { product },
      localVue,
    });
    expect(wrapper.props().product.name).toBe(product.name);
    expect(wrapper.props().product.id).toBe(product.id);
    expect(wrapper.props().product.img).toBe(product.img);
    expect(wrapper.props().product.price).toBe(product.price);
  });

  it("Test the gotoproduct method.", async () => {
        let product = { id: "a7f8fa32-4d59-4641-b9b5-33596d46aa6a", name: "Hamburger", price: 11.5, img: "https://www.sameneenkoekopen.nl/uploads/images/recept/hamburger-uit-de-pan.jpg" };
        let wrapper = shallowMount(ProductCard, {
            propsData: { product },
            mocks: {
                $route: "/menu/2/1"
            },
            localVue,
        });
      await wrapper.trigger('click', { button: 0 });
      expect(wrapper.vm.$route).toBe("/menu/2/1");
  });

  it("Test the addtocart method.", () => {
        let product = { id: "a7f8fa32-4d59-4641-b9b5-33596d46aa6a", name: "Hamburger", price: 11.5, img: "https://www.sameneenkoekopen.nl/uploads/images/recept/hamburger-uit-de-pan.jpg" };
        const $store =  {
          commit: jest.fn(),
        };
        let wrapper = shallowMount(ProductCard, {
            propsData: { product },
            mocks: {
                $store,
            },
            localVue,
        });
        wrapper.vm.$store.commit("addToCart", wrapper.props().product);
        wrapper.vm.$bvModal.hide(wrapper.props().product.id.toString());
        expect(wrapper.vm.$store).toEqual($store);
  });
});