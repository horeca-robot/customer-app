import { shallowMount } from "@vue/test-utils";
import Navbar from "@/components/Navbar.vue";

describe("Navbar.vue", () => {
  it("Test the changing text in navbar.", () => {
    let route = { name: "Menu" };
    let wrapper = shallowMount(Navbar, {
      propsData: { route },
    });
    expect(wrapper.text()).toMatch(route.name);
    route = { name: "Categorie" };
    wrapper = shallowMount(Navbar, {
      propsData: { route },
    });
    expect(wrapper.text()).toMatch(route.name);
  });
});
