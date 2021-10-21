import { shallowMount } from "@vue/test-utils";
import TableQrScanner from "@/components/TableQrScanner";

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
    });
    await wrapper.vm.onDecode("http://localhost:8080/tablevalidator/testCode");
    expect($router.push).toHaveBeenCalledWith({ name: "menu" });
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
    });
    await wrapper.vm.onDecode("http://localhost:8080/badrequest/nonecode");
    expect(wrapper.vm.$data.isValid).toEqual(false);
  });
});
