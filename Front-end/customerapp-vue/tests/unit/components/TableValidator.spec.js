import { shallowMount, createLocalVue } from "@vue/test-utils";
import TableValidator from "../../../src/views/TableValidator";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

const localVue = createLocalVue();  
localVue.use(BootstrapVue);

describe("TableValidator.vue", () => {
  it("Test table validator by url success.", async () => {
    const $route = {
      params: {
        tableId: "TestCode",
      },
    };
    const $router = {
      push: jest.fn(),
    };
    const $store = {
      dispatch: jest.fn(),
    };
    shallowMount(TableValidator, {
      mocks: {
        $router,
        $route,
        $store,
      },
      localVue,
    });

    expect($store.dispatch).toHaveBeenCalledWith(
      "tableModule/setTable",
      "TestCode"
    );
    expect($router.push).toHaveBeenCalledWith({ name: "menu" });
  });

  it("Test table validator by url failed.", async () => {
    const $router = {
      push: jest.fn(),
    };
    shallowMount(TableValidator, {
      mocks: {
        $router,
      },
      localVue,
    });

    expect($router.push).toHaveBeenCalledWith({ name: "table-picker" });
  });
});
