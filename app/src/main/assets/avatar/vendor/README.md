# Bundled avatar runtime

These browser files are bundled for offline WebView use:

- `three.min.js`: Three.js r160
- `three-vrm.js`: `@pixiv/three-vrm` 2.1.0
- `GLTFLoader.js`: classic browser loader distribution

Keep these files local and version-pinned. Do not replace them with remote CDN
URLs in `index.html`; the VRM must continue to work without network access.