import { shallowMount, createLocalVue } from "@vue/test-utils";
import TableListPicker from "@/components/TableListPicker";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

const localVue = createLocalVue();  
localVue.use(BootstrapVue);

describe("TableListPicker.vue", () => {
  it("Test selecting a table manually.", async () => {
    const $router = {
      push: jest.fn(),
    };
    const $store = {
      dispatch: jest.fn(),
    };
    const confirmtable ={
      show: jest.fn(),
      render: () => {},
    };
    const wrapper = shallowMount(TableListPicker, {
      propsData: {
        tables: [{ id: "TestCode", number: 1 }],
      },
      stubs: {
        confirmtable,
      },
      mocks: {
        $router,
        $store,
      },
      localVue,
    });
    await wrapper.find(".table-item").trigger("click");
    expect($store.dispatch).toHaveBeenCalledWith(
      "tableModule/setTable",
      "TestCode"
    );
    expect($router.push).toHaveBeenCalledWith({ name: "menu" });
  });
});
