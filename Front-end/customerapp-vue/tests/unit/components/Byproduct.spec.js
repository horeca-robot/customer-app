import { shallowMount, createLocalVue } from "@vue/test-utils";
import Byproduct from "@/components/Byproduct.vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

const localVue = createLocalVue();  
localVue.use(BootstrapVue);
localVue.use(IconsPlugin);

describe("Byproduct.vue", () => {
  it("Test inserting a byproduct.", () => {
    let byproduct = { name: "Tomaat" };
    let wrapper = shallowMount(Byproduct, {
      propsData: { byproduct },
      localVue,
    });
    expect(wrapper.text()).toMatch(byproduct.name);
  });

  it("Test amount changing.", async () => {
    let byproduct = { name: "Tomaat" };
    let wrapper = shallowMount(Byproduct, {
      propsData: { byproduct },
      localVue,
    });
    expect(wrapper.vm.amount.toString()).toMatch("0");
    wrapper.vm.AddToAmount();
    expect(wrapper.vm.amount.toString()).toMatch("1");
    wrapper.vm.RemoveFromAmount();
    expect(wrapper.vm.amount.toString()).toMatch("0");
  });

  it("Test amount not below 0.", async () => {
    let byproduct = { name: "Tomaat" };
    let wrapper = shallowMount(Byproduct, {
      propsData: { byproduct },
      localVue,
    });
    expect(wrapper.vm.amount.toString()).toMatch("0");
    wrapper.vm.RemoveFromAmount();
    expect(wrapper.vm.amount.toString()).toMatch("0");
  });
});