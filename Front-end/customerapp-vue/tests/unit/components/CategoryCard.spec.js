import { shallowMount } from "@vue/test-utils";
import CategoryCard from "@/components/CategoryCard.vue";

describe("CategoryCard.vue", () => {
    it("Test Different category names on card.", () => {
      var category = {id: 3, name: "Dessert"};
      var wrapper = shallowMount(CategoryCard, {
        propsData: { category },
      });
      expect(wrapper.text()).toMatch(category.name);
      category = {id: 1, name: "Appetizer"}
      wrapper = shallowMount(CategoryCard, {
        propsData: { category },
      });
      expect(wrapper.text()).toMatch(category.name);
    });

    it("Test button to specific category", () =>{
        var category = {id: 1, name: "Appetizer"}
        const wrapper = shallowMount(CategoryCard, {
            propsData: { category},
            mocks: {
                $route: "/menu/"+category.id
            },
        });
        wrapper.trigger('click', {button: 0});
        expect(wrapper.vm.$route).toBe("/menu/1");
    })
  });
  