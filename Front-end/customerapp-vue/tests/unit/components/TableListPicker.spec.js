import { shallowMount } from "@vue/test-utils";
import TableListPicker from "@/components/TableListPicker";

describe("TableListPicker.vue", () => {
  it("Test selecting a table manually.", async () => {
    const $router = {
      push: jest.fn(),
    };
    const $store = {
      dispatch: jest.fn(),
    };
    const wrapper = shallowMount(TableListPicker, {
      propsData: {
        tables: [{ id: "TestCode", number: 1 }],
      },
      mocks: {
        $router,
        $store,
      },
    });
    await wrapper.find(".table-item").trigger("click");
    expect($store.dispatch).toHaveBeenCalledWith(
      "tableModule/setTable",
      "TestCode"
    );
    expect($router.push).toHaveBeenCalledWith({ name: "menu" });
  });
});
