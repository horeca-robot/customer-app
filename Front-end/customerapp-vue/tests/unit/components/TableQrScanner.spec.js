import { shallowMount, createLocalVue } from "@vue/test-utils";
import TableQrScanner from "@/components/TableQrScanner";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

const localVue = createLocalVue();  
localVue.use(BootstrapVue);
describe("TableQrScanner.vue", () => {
  it("Test decoding QR scan code success.", async () => {
    const $router = {
      push: jest.fn(),
    };
    const $store = {
      dispatch: jest.fn(),
    };
    const wrapper = shallowMount(TableQrScanner, {
      mocks: {
        $router,
        $store,
      },
      localVue,
    });
    await wrapper.vm.onDecode("http://localhost:8080/tablevalidator/testCode");
    expect($router.push).toHaveBeenCalledWith({ name: "Menu" });
    expect($store.dispatch).toHaveBeenCalledWith(
      "tableModule/setTable",
      "testCode"
    );
  });

  it("Test decoding QR scan code failed.", async () => {
    const wrapper = shallowMount(TableQrScanner, {
      mocks: {
        isValid: undefined,
      },
      localVue,
    });
    await wrapper.vm.onDecode("http://localhost:8080/badrequest/nonecode");
    expect(wrapper.vm.$data.isValid).toEqual(false);
  });
});
