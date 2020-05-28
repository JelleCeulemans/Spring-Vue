<style lang="scss">
  .popup-wrapper {
    &:not(.open) {
      display:none;
    }

    .popup {
      position: relative;
      margin: 20vh auto;
      background:white;
      max-width: 700px;
      min-height: 100px;
      border-radius: 5px;
      box-shadow: 0px 0px 3px 3px rgba(0, 0, 0, 0.05);
      border: 1px solid #E7E7E7;

      .close {
        position:absolute;
        top: 10px;
        right: 15px;
        z-index:99;
      }
    }
  }
</style>

<template>
  <div class="popup-wrapper" :class="{ open }">
    <div class="popup">
      <button @click="$emit('close')" type="button" class="close" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>

      <div class="popup-body">
        <slot/>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    open: {
      type: Boolean,
      default: false,
    },
  },
};
</script>
