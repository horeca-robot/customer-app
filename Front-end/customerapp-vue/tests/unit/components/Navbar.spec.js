import { shallowMount, createLocalVue } from "@vue/test-utils";
import Navbar from "@/components/Navbar.vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

const localVue = createLocalVue();  
localVue.use(BootstrapVue);

describe("Navbar.vue", () => {
  it("Test the changing text in navbar.", () => {
    let route = { name: "Menu" };
    let wrapper = shallowMount(Navbar, {
      propsData: { route },
      localVue,
    });
    expect(wrapper.text()).toMatch(route.name);
    route = { name: "Categorie" };
    wrapper = shallowMount(Navbar, {
      propsData: { route },
      localVue,
    });
    expect(wrapper.text()).toMatch(route.name);
  });
});