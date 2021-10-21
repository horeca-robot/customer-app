import { mount, shallowMount } from "@vue/test-utils";
import Menu from "@/views/Menu.vue";
import { axios } from "axios";

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
          }
      });
      await wrapper.setData({categories: categories});
      expect(wrapper.vm.categories).toEqual(categories);
    });
  });