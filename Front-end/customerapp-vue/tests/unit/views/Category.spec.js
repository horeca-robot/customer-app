import { mount, shallowMount, createLocalVue } from "@vue/test-utils";
import Categoryview from "@/views/Category.vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

const localVue = createLocalVue();  
localVue.use(BootstrapVue);
localVue.use(IconsPlugin);

describe("Category.vue", ()=>{
    it("Test if correct amount of products are made", async() => {
        var pizza = {id: 1, name: "Pizza", price: 12.34};
        var coffee = {id: 3, name: "Coffee", price: 4.54};
        var Products = [pizza, coffee];
        var category = {name: "dessert", id: 1, img: "https://www.24kitchen.nl/files/styles/social_media_share/public/2020-08/verse-tomatensoep.jpg?itok=AqWg5Ju0" ,products: Products}
        const $APIService =  {
            getCategoryById: ()=> Promise.resolve({data: category}), 
        };
        const $store = {
            commit: jest.fn(),
        }
        const $route = {
            params: {
                id: 10,
            }
        }
        var wrapper = shallowMount(Categoryview, {
            mocks: {
            $APIService,
            $store,
            $route,
            },
            localVue,
        });
        await wrapper.setData({category: category});
        expect(wrapper.vm.category.products).toEqual(Products)
    });
})