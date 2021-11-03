import { mount, shallowMount } from "@vue/test-utils";
import Productview from "@/views/Product.vue";
import { axios } from "axios";

describe("Product.vue", () => {
    it("Test if correct amount of byproducts are made", async () =>{
        var fries = {id: 1, name: "fries", price: 2.34};
        var salade = {id: 2, name: "salade", price: 5.45};
        var Byproducts = [salade, fries];
        var Product = {id: 3, name: "Hamburger", price: 14.54, img: "https://www.24kitchen.nl/files/styles/social_media_share/public/2020-08/verse-tomatensoep.jpg?itok=AqWg5Ju0", byproducts: Byproducts};

        const axios =  {
            get: ()=> Promise.resolve({data: Product}),
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
              axios,
              $store,
              $route,
            }
        });
        await wrapper.setData({product: Product});
        expect(wrapper.vm.product.byproducts).toEqual(Byproducts);
    }),
    it("test Gotocategory", async () =>{
        var Product = {id: 3, name: "Hamburger", price: 14.54};
        const $store = {
            commit: jest.fn(),
            getters: { cartItems: 0, cartTotal: 0 },
          };
          const axios =  {
            get: ()=> Promise.resolve({data: Product}), 
        };
        const $route = {
            params: {
                categoryId: 1,
                route: "/menu/1",
            }
        }
        let wrapper = shallowMount(Productview, {
        mocks: {
            $route,
            axios,
            $store,
        }
        });
        await wrapper.trigger('click', { button: 0 });
        expect(wrapper.vm.$route.params.route).toBe("/menu/1");
    })
})