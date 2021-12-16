import { mount, shallowMount, createLocalVue } from "@vue/test-utils";
import Productview from "@/views/Product.vue";
import { axios } from "axios";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import { RouterLinkStub } from "@vue/test-utils";

const localVue = createLocalVue();  
localVue.use(BootstrapVue);
localVue.use(IconsPlugin);

describe("Product.vue", () => {
    it("Test if correct amount of byproducts are made", async () =>{
        var fries = {id: 1, name: "fries", price: 2.34};
        var salade = {id: 2, name: "salade", price: 5.45};
        var Byproducts = [salade, fries];
        var Ingredients = ["Sla", "ui", "kaas", "broodje", "burger"]
        var Product = {id: 3, name: "Hamburger", price: 14.54, img: "https://www.24kitchen.nl/files/styles/social_media_share/public/2020-08/verse-tomatensoep.jpg?itok=AqWg5Ju0", byproducts: Byproducts};
        var dinner = {name: "dinner", products: [fries]};
        var icecream = {name: "icecream", products: [salade]};
        var categories = [dinner, icecream];

        const $APIService =  {
            getProductById: ()=> Promise.resolve({data: Product}),
            getIngredientsByProductId: ()=> Promise.resolve({data: Ingredients}),
            getAllParentCategories: ()=> Promise.resolve({data: categories})
        }
        const $store = {
            commit: jest.fn(),
        }
        const $route = {
            params: {
                id: 10,
            }
        }
        var wrapper = shallowMount(Productview, {
            mocks: {
              $APIService,
              $store,
              $route,
            },
            stubs:{
                RouterLink: RouterLinkStub,
            },
            localVue,
        });
        await wrapper.setData({product: Product});
        expect(wrapper.vm.product.byproducts).toEqual(Byproducts);
    }),
    it("test Gotocategory", async () =>{
        var Product = {id: 3, name: "Hamburger", price: 14.54};
        var Ingredients = ["kaas", "ham", "deeg", "tomatensaus"];
        var dinner = {name: "dinner", products: [Product]};
        var icecream = {name: "icecream", products: [Product]};
        var categories = [dinner, icecream];
        const $store = {
            commit: jest.fn(),
            getters: { cartItems: 0, cartTotal: 0 },
          };
          const $APIService =  {
            getProductById: ()=> Promise.resolve({data: Product}),
            getIngredientsByProductId: ()=> Promise.resolve({data: Ingredients}),
            getAllParentCategories: ()=> Promise.resolve({data: categories}),
        };
        const $route = {
            params: {
                categoryId: 1,
                route: "/Menu/1",
            }
        }
        let wrapper = shallowMount(Productview, {
        mocks: {
            $route,
            $APIService,
            $store,
        },
        stubs:{
            RouterLink: RouterLinkStub,
        },
        localVue,
        });
        await wrapper.trigger('click', { button: 0 });
        expect(wrapper.vm.$route.params.route).toBe("/Menu/1");
    })
})