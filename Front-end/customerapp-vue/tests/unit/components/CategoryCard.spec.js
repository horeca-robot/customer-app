import { shallowMount, createLocalVue } from "@vue/test-utils";
import CategoryCard from "@/components/CategoryCard.vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

const localVue = createLocalVue();  
localVue.use(BootstrapVue);

describe("CategoryCard.vue", () => {
    it("Test Different category names on card.", () => {
      var category = {id: 3, name: "Dessert"};
      var wrapper = shallowMount(CategoryCard, {
        propsData: { category },
        localVue,
      });
      expect(wrapper.text()).toMatch(category.name);
      category = {id: 1, name: "Appetizer"}
      wrapper = shallowMount(CategoryCard, {
        propsData: { category },
        localVue,
      });
      expect(wrapper.text()).toMatch(category.name);
    });

    it("Test button to specific category", async () =>{
        var category = {id: 1, name: "Appetizer"}
        const $router = {
          push: jest.fn(),
        };
        const wrapper = shallowMount(CategoryCard, {
            propsData: { category},
            mocks: {
                $route: "/menu/"+category.id,
                $router,
            },
            localVue,
        });
        await wrapper.trigger('click', {button: 0});
        expect(wrapper.vm.$route).toBe("/menu/1");
    })
  });
  