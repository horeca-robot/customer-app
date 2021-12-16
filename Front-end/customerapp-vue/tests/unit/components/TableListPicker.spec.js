import { shallowMount, createLocalVue } from "@vue/test-utils";
import TableListPicker from "@/components/TableListPicker";
import { BootstrapVue } from "bootstrap-vue";

const localVue = createLocalVue();
localVue.use(BootstrapVue);

describe("TableListPicker.vue", () => {
  it("Test selecting a table, gives popup.", async () => {
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
      localVue,
    });
    wrapper.vm.$refs.confirmtable.show = jest.fn();

    await wrapper.find(".table-item").trigger("click");
    expect(wrapper.vm.selectedTable).toEqual({ id: "TestCode", number: 1 });
  });

  it("Test redirecting to table", async () => {
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
      localVue,
    });
    await wrapper.vm.redirectToTable({ id: "TestCode", number: 1 });
    expect($store.dispatch).toHaveBeenCalledWith(
        "tableModule/setTable",
        "TestCode"
    );
    expect($router.push).toHaveBeenCalledWith({ name: "Menu" });
  });
});
