import { shallowMount, createLocalVue } from "@vue/test-utils";
import searchbar from "@/custom-tags/searchbar.vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

const localVue = createLocalVue();  
localVue.use(BootstrapVue);
localVue.use(IconsPlugin);

describe("searchbar.vue", () => {
    it("test if correct number of results are shown from input", () =>{
        var input = "";
        var hamburger = {name: "hamburger", price: 2.34}
        var pizza = {name: "pizza", price: 24.2}
        var icecream = {name: "icecream", price: 1.34};
        var products = [hamburger, pizza, icecream];
        var filteredProducts = [products];
        var categories = ["dinner", "icecream", "vegetarian"];

        const $APIService = {
            getAllCategories: ()=> Promise.resolve({data: categories}),
            getAllProducts: ()=> Promise.resolve({data: products}),
        }

        let wrapper = shallowMount(searchbar, {
            propsData: {
                input,
                filteredProducts,
            },
            mocks: {
                $APIService
            },
            localVue,
        });
        expect(wrapper.props(filteredProducts.length).tobe(products.length));

    })
})