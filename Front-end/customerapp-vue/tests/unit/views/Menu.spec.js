import { mount, shallowMount, createLocalVue } from "@vue/test-utils";
import Menu from "@/views/Menu.vue";
import { axios } from "axios";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

const localVue = createLocalVue();  
localVue.use(BootstrapVue);
localVue.use(IconsPlugin);

describe("Menu.vue", () => {
    it("Test if correct amount of cards are made.", async() => {
      var appetizer = {id: 1, name: "appetizer"};
      var dessert = {id: 3, name: "dessert"};
      var categories = {appetizer, dessert};
      const axios =  {
          get: ()=> Promise.resolve({data: categories}),
      }
      const $store = {
          commit: jest.fn(),
      }
      var wrapper = shallowMount(Menu, {
          mocks: {
            axios,
            $store,
          },
          localVue,
      });
      await wrapper.setData({categories: categories});
      expect(wrapper.vm.categories).toEqual(categories);
    });
  });