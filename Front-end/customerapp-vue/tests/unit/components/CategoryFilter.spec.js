import { shallowMount, createLocalVue } from "@vue/test-utils";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import CategoryFilter from "../../../src/components/CategoryFilter";

const localVue = createLocalVue();
localVue.use(BootstrapVue);
localVue.use(IconsPlugin);

describe("CategoryFilter.vue", () => {
  it("Test adding a new tag.", async () => {
    const $router = {
      push: jest.fn(),
    };
    const $store = {
      dispatch: jest.fn(),
      state: {
        tagFilter: {
          selectedFilters: [""],
        },
      },
    };
    const $APIService = {
      getAllTags: () => Promise.resolve({}),
    };
    const wrapper = shallowMount(CategoryFilter, {
      mocks: {
        $router,
        $store,
        $APIService,
      },
      localVue,
    });
    wrapper.vm.selectedTag = "ABC";

    await wrapper.vm.addTagFilter();
    expect($store.dispatch).toHaveBeenCalledTimes(1);
  });

  it("Test adding existing item.", async () => {
    const $router = {
      push: jest.fn(),
    };
    const $store = {
      dispatch: jest.fn(),
      state: {
        tagFilter: {
          selectedFilters: ["ABC"],
        },
      },
    };
    const $APIService = {
      getAllTags: () => Promise.resolve({}),
    };
    const wrapper = shallowMount(CategoryFilter, {
      mocks: {
        $router,
        $store,
        $APIService,
      },
      localVue,
    });
    wrapper.vm.selectedTag = "ABC";

    await wrapper.vm.addTagFilter();
    expect($store.dispatch).toHaveBeenCalledTimes(0);
  });

  it("Test adding null tag filter.", async () => {
    const $router = {
      push: jest.fn(),
    };
    const $store = {
      dispatch: jest.fn(),
      state: {
        tagFilter: {
          selectedFilters: [""],
        },
      },
    };
    const $APIService = {
      getAllTags: () => Promise.resolve({}),
    };
    const wrapper = shallowMount(CategoryFilter, {
      mocks: {
        $router,
        $store,
        $APIService,
      },
      localVue,
    });
    await wrapper.vm.addTagFilter();
    expect($store.dispatch).toHaveBeenCalledTimes(0);
  });

  it("Test removing null tag.", async () => {
    const $router = {
      push: jest.fn(),
    };
    const $store = {
      dispatch: jest.fn(),
      state: {
        tagFilter: {
          selectedFilters: [""],
        },
      },
    };
    const $APIService = {
      getAllTags: () => Promise.resolve({}),
    };
    const wrapper = shallowMount(CategoryFilter, {
      mocks: {
        $router,
        $store,
        $APIService,
      },
      localVue,
    });

    await wrapper.vm.removeTagFilter(null);
    expect($store.dispatch).toHaveBeenCalledTimes(0);
  });

  it("Test removing tag.", async () => {
    const $router = {
      push: jest.fn(),
    };
    const $store = {
      dispatch: jest.fn(),
      state: {
        tagFilter: {
          selectedFilters: [""],
        },
      },
    };
    const $APIService = {
      getAllTags: () => Promise.resolve({}),
    };
    const wrapper = shallowMount(CategoryFilter, {
      mocks: {
        $router,
        $store,
        $APIService,
      },
      localVue,
    });

    await wrapper.vm.removeTagFilter("ABC");
    expect($store.dispatch).toHaveBeenCalledTimes(1);
  });
});
