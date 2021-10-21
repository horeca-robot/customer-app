import { shallowMount } from "@vue/test-utils";
import TableValidator from "../../../src/views/TableValidator";

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
    });

    expect($router.push).toHaveBeenCalledWith({ name: "table-picker" });
  });
});
